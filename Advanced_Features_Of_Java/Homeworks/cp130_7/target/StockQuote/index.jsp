<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form id="get-quote" action="http://localhost:8080/StockQuote/welcome" method="post">
    Stock Symbol:
    <input type="text" name=stockQuote size=8>
    </input>
    <br>
    Format:
    <input type="radio" name="format" value="html">HTML
    <input type="radio" name="format" value="json" checked>JSON
    <input type="radio" name="format" value="plain">Plain
    <input type="radio" name="format" value="xml">XML
    <p>
        <input type="submit" name="submit_btn" value="Get Quote">
</form>

</body>
</html>