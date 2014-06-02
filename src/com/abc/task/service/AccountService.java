package com.abc.task.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.abc.task.enums.MCAccount;
import com.abc.task.enums.UCAccount;
import com.abc.task.enums.UCAccountLogStatus;
import com.abc.task.enums.WealthType;
import com.abc.task.exception.DataBaseException;
import com.abc.task.exception.ParameterException;
import com.abc.task.vo.AccountLog;

@Service("accountService")
public class AccountService {
	private static final Log log = LogFactory.getLog(AccountService.class);
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void insertAccountTmpLog(List<AccountLog> logs)
			throws DataBaseException {
		List<Object[]> ls = new ArrayList<Object[]>(logs.size());
		for (AccountLog l : logs) {
			int memberId = l.getMemberId();
			String mebmerName = l.getMemberName();
			int merchantId = l.getMerchantId();
			String merchantName = l.getMerchantName();
			int mcAccountId = l.getMcAccountId();
			WealthType wt = l.getWealthType();
			MCAccount mcAccount = l.getMcAccount();
			UCAccount ucAccount = l.getUcAccount();
			int ucwealth = l.getUcWealth();
			int mcwealth = l.getMcWealth();
			String sn = l.getSerialNumber();
			String ssn = l.getSubSerialNumber();
			Date ct = l.getCreateTime();
			UCAccountLogStatus status = l.getStatus();
			int delayHours = l.getDelayHours();
			String remark = l.getRemark();
			String operator = l.getOperator();
			ls.add(new Object[] { memberId, mebmerName, merchantId,
					merchantName, mcAccountId, wt.name(), mcAccount.name(),
					ucAccount.name(), ucwealth, mcwealth, sn, ssn, ct,
					status.name(), delayHours, remark, operator });
		}
		try {
			jdbcTemplate.batchUpdate("INSERT INTO account_log_tmp("
					+ "member_id,member_name,merchant_id,"
					+ "merchant_name,mc_account_id,wealth_type,"
					+ "mc_account,uc_account,uc_wealth,"
					+ "mc_wealth,serial_number,sub_serial_number,"
					+ "create_time,status,delay_hours,remark,operator) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", ls);
			jdbcTemplate.batchUpdate("INSERT INTO account_log_period("
					+ "member_id,member_name,merchant_id,"
					+ "merchant_name,mc_account_id,wealth_type,"
					+ "mc_account,uc_account,uc_wealth,"
					+ "mc_wealth,serial_number,sub_serial_number,"
					+ "create_time,status,delay_hours,remark,operator) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", ls);
		} catch (Exception e) {
			throw new DataBaseException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> userScore(int memberId) {
		try {
			return jdbcTemplate.queryForMap(
					"select * from uc_account where member_id = ?", memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return MapUtils.EMPTY_MAP;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> scoreLogs(int page, int rowCount,
			int itemId, int memberId, Date begin, Date end) {
		if (rowCount <= 0 || page <= 0) {
			throw new ParameterException("rowcount or page must be big than 0!");
		}
		StringBuilder sql = new StringBuilder(100);
		sql.append("select * from uc_account_log where member_id = ? order by audit_time desc");
		List<Object> l = new ArrayList<Object>(3);
		l.add(memberId);
		if (itemId > 0) {
			sql.append(" and item_id = ? ");
			l.add(itemId);
		}
		if (begin != null) {
			sql.append(" and audit_time >= ? ");
			l.add(begin);
		}
		if (end != null) {
			sql.append(" and audit_time < ? ");
			l.add(end);
		}
		sql.append(" limit ").append((page - 1) * rowCount).append(",")
				.append(rowCount);
		try {
			return jdbcTemplate.queryForList(sql.toString(), l.toArray());
		} catch (DataAccessException e) {
			log.error(e, e);
			return ListUtils.EMPTY_LIST;
		}
	}

	public int scoreLogCount(int itemId, int memberId, Date begin, Date end) {
		StringBuilder sb = new StringBuilder(100);
		sb.append("select count(*) from uc_account_log where member_id = ? ");
		List<Object> l = new ArrayList<Object>(3);
		l.add(memberId);
		if (itemId > 0) {
			sb.append(" and item_id = ? ");
			l.add(itemId);
		}
		if (begin != null) {
			sb.append(" and audit_time >= ? ");
			l.add(begin);
		}
		if (end != null) {
			sb.append(" and audit_time < ? ");
			l.add(end);
		}
		try {
			return jdbcTemplate.queryForInt(sb.toString(), l.toArray());
		} catch (DataAccessException e) {
			log.error(e, e);
			return 0;
		}
	}

	public int userDelayScore(int memberId) {
		try {
			return jdbcTemplate
					.queryForInt(
							"select sum(wealth) from uc_delay_account_log where member_id = ?",
							memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> delayScoreLogs(int page, int rowCount,
			int memberId) {
		if (rowCount <= 0 || page <= 0) {
			throw new ParameterException("rowcount or page must be big than 0!");
		}
		StringBuilder sql = new StringBuilder(
				"select * from uc_delay_account_log where member_id = ? order by sub_serial_number");
		sql.append(" limit ").append((page - 1) * rowCount).append(",")
				.append(rowCount);
		try {
			return jdbcTemplate.queryForList(sql.toString(), memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return ListUtils.EMPTY_LIST;
		}
	}

	public int delayScoreLogCount(int memberId) {
		try {
			return jdbcTemplate
					.queryForInt(
							"select count(*) from uc_delay_account_log where member_id = ?",
							memberId);
		} catch (DataAccessException e) {
			log.error(e, e);
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> accountPeriodList(int count,
			WealthType type, Date begin, Date end) {
		String sql = "select member_name name,sum(uc_wealth) wealth from account_log_period where wealth_type = ? and create_time >= ? and create_time < ? group by member_name order by sum(uc_wealth) desc limit 0, ?";
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(end);
			c.add(Calendar.DAY_OF_MONTH, +1);
			return jdbcTemplate.queryForList(sql,type.name(),begin,c.getTime(),count);
		} catch (DataAccessException e) {
			log.error(e, e);
			return ListUtils.EMPTY_LIST;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> accountPeriodLogs(int count,
			WealthType type, Date begin, Date end) {
		String sql = "select member_name name,uc_wealth wealth from account_log_period where wealth_type = ? and create_time >= ? and create_time < ?  order by create_time desc limit 0, ?";
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(end);
			c.add(Calendar.DAY_OF_MONTH, +1);
			return jdbcTemplate.queryForList(sql,type.name(),begin,c.getTime(),count);
		} catch (DataAccessException e) {
			log.error(e, e);
			return ListUtils.EMPTY_LIST;
		}
	}
}
