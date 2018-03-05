<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>my websocket</title>
</head>
<body>
<div id='msgcount'></div>
<script type="text/javascript" src='./jquery.js'></script>
        <script>
            var websocket;
            websocket = new WebSocket("ws://localhost:8080/project-model/project/users");
            websocket.onopen = function (evnt) {
            };
            websocket.onmessage = function (evnt) {
            	//console.log(evnt.data)
                $("#msgcount").append("(<font color='red'>"+evnt.data+"</font>)<br/>")
                //document.getElementById('msgcount').innerHTML = "(<font color='red'>"+evnt.data+"</font>)"
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
            };
        </script>
</body>
</html>