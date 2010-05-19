<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head><title>Add Person</title></head>
    <body>
        <%@include file="/index.jsp" %>
        <form name="addperson" action="/eventmanager" method="post">
            <input type="hidden" name="actionparameter" value="person">
            <table>
                <tr>
                    <td align="right">First Name: </td>
                    <td align="left">
                        <input type="text" name="firstname" size="30">
                    </td>
                </tr>
                <tr>
                    <td align="right">Last Name: </td>
                    <td align="left">
                        <input type="text" name="lastname" size="30">
                    </td>
                </tr>
                <tr>
                    <td align="right">Age: </td>
                    <td align="left">
                        <input type="text" name="age" size="30">
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