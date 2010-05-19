<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head><title>Add Person to Event</title></head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <body>
        <%@include file="/index.jsp" %>
        <form name="addpersontoevent" action="/eventmanager" method="post">
            <input type="hidden" name="actionparameter" value="persontoevent">
            <table>
                <tr>
                    <td align="right">Select Person: </td>
                    <td align="left">
                        <select name="personlist">
                            <c:forEach items="${personlist}" var="person">
                                <option id="${person.id}">${person.firstname} ${person.lastname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">Select Event: </td>
                    <td align="left">
                        <select name="eventlist">
                            <c:forEach items="${eventlist}" var="event">
                                <option id="${event.id}">${event.title}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" name="submit" value="Add">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>