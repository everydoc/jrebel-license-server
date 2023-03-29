<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>License Server</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-light bg-light">
        <a class="navbar-brand" href="/">
            <img src="/favicon.ico" width="30" height="30" class="d-inline-block align-top" alt="Jrebel">
            Jrebel
        </a>
    </nav>

    <div class="jumbotron">
        <h2 class="display-5">Jrebel & JetBrains License Server!</h2>
        <p>JRebel 2018.1 and later version Activation address is: <span style='color:red'><script>document.write(window.location.href)</script>${uuid}</span></p>

        <p>
        <#list jrebel.social as s>
            <span><a href="${s.url}">${s.name}</a></span>&nbsp;&nbsp;
        </#list>
        </p>


        <p>关注公众号【天府书虫】，发送关键字"jrebel"获取激活地址。  </p>
        <img src="/wechat.jpg" class="img-fluid rounded" alt="微信公众号">
<#--        <img src="/mini-program.jpg" class="img-fluid rounded" alt="小程序">-->
        <div id="repos"></div>
        <p>
            <a class="btn btn-primary btn-lg float-right" href="https://github.com/imjcker/jrebel-license-server" role="button">Fork me on GitHub</a>
        </p>
    </div>
</div>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/popper.js/1.16.0/umd/popper.min.js" ></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
<#--<script src="https://unpkg.com/browse/marked@4.3.0/marked.min.js"></script>-->
<script src="https://cdn.jsdelivr.net/npm/marked@4.3.0/lib/marked.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js"></script>
<script>
    window.onload = function () {
        new Vue({
            el: '#repos',
            created() {
                console.log('Get github repos');
                fetch('https://api.github.com/users/everydoc/repos')
                    .then(response => response.json())
                    .then(result => {
                        let prePre = '<a href="https://github.com/' ;
                        let preMid = '" target="_blank" >';
                        let prePost = '</a>';
                        let pre = '<img src="https://img.shields.io/github/stars/';
                        let post = '.svg?style=social">';
                        let repoList = result.sort((x,d)=>d.stargazers_count - x.stargazers_count).map(repo => {
                            return {
                                url: prePre + repo.full_name + preMid + repo.name + prePost,
                                star: pre + repo.full_name + post,
                                memo: repo.description
                            }
                        });
                        let table = '|项目名称 |星星数量 |项目描述 | \n|:----|:----:|:----|\n';
                        repoList.forEach(r => {
                            table += '| ' + r.url + ' |' + r.star + ' |' + r.memo + ' \n'
                        });
                        document.getElementById('repos').innerHTML = marked.parse(table);
                    })
                    .catch(err => console.log(err));
            }
        })
    };
</script>
</body>
</html>
