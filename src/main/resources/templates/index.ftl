<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>License Server</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="jumbotron" style="margin-top: 64px;">
        <h1 class="display-4">Jrebel & JetBrains License Server!</h1>
        <p class="lead">License Server started at ${url}</p>
        <p class="my-4">JetBrains Activation address was: <span style='color:red'>${url}</span></p>
        <p>JRebel 7.1 and earlier version Activation address was: <span style='color:red'>${url}/{tokenname}</span>, with any email.</p>
        <p>JRebel 2018.1 and later version Activation address was: <span style='color:red'>${url}/${uuid}</span></p>
        <a class="btn btn-primary btn-lg" href="https://github.com/imjcker/jrebel-license-server" role="button">Fork me on GitHub</a>

        <a class="btn btn-primary btn-lg" href="https://hub.docker.com/repository/docker/imjcker/jrebel" role="button">Set up with Docker</a>

    </div>
</div>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/popper.js/1.16.0/umd/popper.min.js" ></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
</body>
</html>