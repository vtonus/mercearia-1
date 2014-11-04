<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link
	href='http://fonts.googleapis.com/css?family=Vollkorn:400italic,700italic,400,700'
	rel='stylesheet' type='text/css'>
 <!-- <link href='http://fonts.googleapis.com/css?family=Vollkorn:400italic,700italic,400,700' rel='stylesheet' type='text/css'> -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,600,300,800,700' rel='stylesheet' type='text/css'>

<title>Menu principal</title>

<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><!-->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../jui/jquery-ui.js"></script>
<script type="text/javascript" src="../js/scripts.js"></script>
<script type="text/javascript" src="../js/handlers.js"></script>
<script type="text/javascript" src="../js/cliente.js"></script>
<script type="text/javascript" src="../js/compra.js"></script>
<script type="text/javascript" src="../js/produto.js"></script>
<script type="text/javascript" src="../js/fornecedor.js"></script>
<script type="text/javascript" src="../js/fornecimento.js"></script>
<script type="text/javascript" src="../js/funcionario.js"></script>
<script type="text/javascript" src="../js/jquery.transit.min.js"></script>
<!-- para fazer os animaes -->
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script>
$( document ).ready(function() {
    if($('#sessao').text()==''){
    	window.alert('Não há sessão aberta, por favor entre novamente');
    	document.write($('#sessao').text());
    	window.location.replace("/mercearia/BemVindo.jsp");	
    }
   
});
function deslogando(){
	$.post( "Deslogando", function(){
		window.location.replace("/mercearia/BemVindo.jsp");	
	} );	
}

</script>
</head>
<span id="sessao" style="display: none;">${usuario}</span>
<body class="body">
	<div id="usertopo">
		Olá, ${usuario}. Seja bem vindo.
		<button class="limpar bVermelho" onclick="deslogando()">Sair</button>
	</div>
	<div id="accordion">
		<img class="opcaoimg" src="../images/Cliente.png" alt="Cliente"
			height="75" width="75" title="Cliente">

		<div>
			<p class="opcao">
				<a target="ctn" onclick="$('.content').load('NovoCliente.jsp')">NOVO</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraCliente.jsp')" target="ctn">EXISTENTE</a>
			</p>
		</div>

		<img class="opcaoimg" class="opcaoimg" src="../images/Compra.png"
			alt="Compra" height="75" width="75" title="Compra">
		<div>
			<p class="opcao">
				<a onclick="chamatelaCompra()" target="ctn">NOVA</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraCompra.jsp')" target="ctn">EXISTENTE</a>
			</p>
		</div>
		<img class="opcaoimg" src="../images/Produto.png" alt="Produto"
			height="75" width="75" title="Produto">
		<div>
			<p class="opcao">
				<a onclick="$('.content').load('NovoProduto.jsp')" target="ctn">NOVO</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraProduto.jsp')" target="ctn">EXISTENTE</a>
			</p>
		</div>
		<img class="opcaoimg" src="../images/Fornecimento.png"
			alt="Fornecimento" height="75" width="75" title="Fornecimento">
		<div>
			<p class="opcao">
				<a onclick="$('.content').load('NovoFornecimento.jsp'); dadosforn='';" target="ctn">NOVA</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraFornecimento.jsp')"
					target="ctn">EXISTENTE</a>
			</p>
		</div>
		<img class="opcaoimg" src="../images/Fornecedor.png" alt="Fornecedor"
			height="75" width="75" title="Fornecedor">
		<div>
			<p class="opcao">
				<a onclick="$('.content').load('NovoFornecedor.jsp')" target="ctn">NOVO</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraFornecedor.jsp')"
					target="ctn">EXISTENTE</a>
			</p>
		</div>
		<img class="opcaoimg" src="../images/Funcionario.png"
			alt="Funcionario" height="75" width="75" title="Funcionario">
		<div>
			<p class="opcao">
				<a onclick="$('.content').load('NovoFuncionario.jsp')" target="ctn">NOVO</a>
			</p>
			<p class="opcao">
				<a onclick="$('.content').load('ProcuraFuncionario.jsp')"
					target="ctn">EXISTENTE</a>
			</p>
		</div>
		<img class="opcaoimg" src="../images/Relatorio.png" alt="Relatorio"
			height="75" width="75" title="Relatório">
		<div class="menu">
			<p class="opcao">
				<a onclick="$('.content').load('Relatorios.jsp')" target="ctn">GERENCIAR</a>
			</p>
		</div>
	</div>
	<div class="content"></div>
	<div id="respostafeed"></div>
	<script>

$("#accordion" ).accordion();
	</script>

</body>
</html>
