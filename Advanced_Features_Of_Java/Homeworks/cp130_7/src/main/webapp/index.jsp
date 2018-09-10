<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="http://localhost:8080/StockQuote/welcome?tickerSymbol=bac&format=html">Shortcut to bac in html</a>
<p>
<a href="http://localhost:8080/StockQuote/welcome?tickerSymbol=bac&format=json">Shortcut to bac in json</a>
<p>
<a href="http://localhost:8080/StockQuote/welcome?tickerSymbol=bac&format=plain">Shortcut to bac in plain</a>
<p>
<a href="http://localhost:8080/StockQuote/welcome?tickerSymbol=bac&format=xml">Shortcut to bac in xml</a>

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