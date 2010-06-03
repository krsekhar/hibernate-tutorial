<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <link rel="stylesheet" href="style.css" type="text/css">
    <head><title>Index Page</title></head>
    <body>
        <h2 class="logo" align="center">Person-Event Database</h2>
        <a href="jsp/addperson.jsp" >Add Person</a>
        <a href="jsp/addevent.jsp">Add Event</a>
        <a href="eventmanager">Add Person To event</a>

        <form action="search" name="searchform" method="get">
            Search: <input class="search" type="text" size="15" name="searchbox">
            <input class="searchButton" type="submit" value="Search">
        </form>
    </body>
</html>