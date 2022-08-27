<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="mail.MailCtl"%>
<htmL>
<style>
    div{
        background-color: #10e6b2;
        padding-bottom: 200px;
    }
    input,textarea{
        width: 100%;
        padding: 20px;
        margin-bottom:20px;
        box-sizing: border-box;
        font-size: 20px;
        font-family: "Times New Roman";

    }
    .mg{
        width: 100%;
        padding: 15px 20px;
        margin: -2px 0;
        box-sizing: border-box;
        height: 213px;
    }
    .button{
        background-color: #029e04;
        border: none;
        color: white;
        margin-top:20px;
        padding: 12px 58px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;

    }
    h1{
        color:#ffffff;
        font-size: 50px;
        margin-bottom: -50px;
        margin-top: -15px;
    }
</style>
<title>Java Mail client</title>
</head>
<body>
<%--<center>--%>
<%--    <button><a href="inbox.jsp">INBOX</a></button>--%>
<%--</center>--%>
<form action="${pageContext.request.contextPath}/MailCtl" method="post">
    <div align="center">
        <br><br>

        <h1>Java Email Client Web Application</h1>
        <br><br><br>
        <h3>
            <%String msg=(String)request.getAttribute("msg");%>
            <font color="#1e90ff"><%=(msg!=null)?msg:""%></font>
        </h3>

        <table style="width: 50% ;">
            <tr>
                <th><input   type="text" name="email" placeholder="Email To.. "></th>
            </tr>
            <tr>
                <th><input type="text" name="subject" placeholder="Subject... "></th>
            </tr>
            <tr>
                <th><textarea class="mg" rows="2" cols="6" name="message" placeholder="Email Body..."></textarea></th>
            </tr>

            <tr>
                <th align="left"><input  class="button" type="submit" name="operation" value="<%=MailCtl.OP_GO%>"></th>
<%--                <th align="left"><button  type="submit">Submit</button></th>--%>
            </tr>
            <tr>
                <th>
                    <p>S. M. Mohaiminul Islam Eraj | ID: 1721499</p>
                    <p><a href="mailto:1721499@iub.edu.bd">1721499@iub.edu.bd</a></p>
                </th>
            </tr>
        </table>
    </div>

</form>
</body>
</htmL>