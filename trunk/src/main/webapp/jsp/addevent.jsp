<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head><title>Add Event</title></head>
    <body>
        <%@include file="/index.jsp" %>
        <form name="addevent" action="/eventmanager" method="post">
            <input type="hidden" name="actionparameter" value="event">
            <table>
                <tr>
                    <td align="right">Title: </td>
                    <td align="left">
                        <input type="text" name="title" size="30">
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