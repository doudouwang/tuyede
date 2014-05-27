<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="/css/style.css" />
</head>
<body>
<c:forEach var="e"  items="${error }">
	${e.key}:${e.value}
</c:forEach>
</body>
</html>