<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Form Layout</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f4;
        }
        .container {
            display: flex;
            width: 90%;
            max-width: 1200px;
            justify-content: space-between;
            gap: 100px;
        }
        .form-container {
            width: 48%;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-container h1 {
            margin-bottom: 20px;
        }
        .form-container label {
            display: block;
            margin-bottom: 10px;
        }
        .form-container input {
            width: 90%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-container button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #0056b3;
        }
        .form-container h3 {
            margin-top: 20px;
        }
        .form-container pre {
            background-color: #f9f9f9;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;

        }
        .result-container {
         width: 95%;
            max-height: 300px;
            overflow-y : auto;
            margin-top: 10px;
            border: 1px solid #add;
            border-radius: 4px;
        }

        .form-container pre{
            background-color: #f9f9f9;
            padding: 10px;
            margin: 0;
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
  <div class="container">
        <div class="form-container">
            <h1>Form 1</h1>
            <form action="/residency" method="post">
                <label for="fnm">입력</label>
                <input type="text" name="fnm" value="${formName1}">
                <button type="submit" name="action" value="button1">버튼</button>
                <h3>결과</h3>
                <div class="result-container">
                    <pre>${result1}</pre>
                </div>
            </form>
        </div>
        <div class="form-container">
            <h1>Form 2</h1>
            <form action="/income" method="post">
                <label for="fnm">입력</label>
                <input type="text" name="fnm" value="${formName2}">
                <button type="submit" name="action" value="button2">버튼</button>
                <h3>결과</h3>
                <div class="result-container">
                                    <pre>${result2}</pre>
                                </div>
            </form>
        </div>
    </div>


</body>
</html>