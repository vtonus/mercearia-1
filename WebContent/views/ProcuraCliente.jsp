<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
	<meta charset="UTF-8" />
	<script src="../js/jquery.js"></script>
	<script src="../js/scripts.js"></script>
	<title>Gerencie um cliente</title>
<script type="text/javascript">
	
	function tipoEvento(tipo, event) {
		var t = document.getElementById("tipo");
		t.value = tipo;
		document.forms[0].submit();
		event.stopPropagation();
		event.preventDefault();
		return false;
	}
	function editavel() {
		
	}
	
</script>
<style>

#dEsquerda
{
	float:left;
	text-align:right;
	line-height: 40px;
	visibility:hidden;
}

#dDireita
{
	float:left;
	line-height: 40px;
	visibility:hidden;
}

#dAbaixo
{
	text-align:right;
	width:300px;
	line-height: 60px;
	visibility:hidden;
}
</style>
</head>
<body>
	<div id="pcliente">
	
		<div class="metadeEsquerda"><p class="margemP"><h1>Gerencie um cliente</h1> 
		
			<form id="meuForm" action="BuscaCliente" method="POST">
					Palavra Chave:
					<input type="text" id="palavraChave" autofocus>
					<select id="parametro">
						<option value="id">ID</option>
						<option value="nome">Nome</option>
						<option value="cpf">CPF</option>
						<option value="telefone">Telefone</option>
						
					</select>
					<input type="button" value="Buscar" onclick="buscaDadosCliente()">	<br /><br />
				<div id="dEsquerda">
					Nome: <br>
					CPF: <br>
					Telefone: <br>
					Email: <br>
					Sexo: <br>
					Data de nascimento: <br>
				</div>	
				<div id="dDireita">
					<input readonly class="edita" type="text" id="nome" name="nome">	<br />
				 	<input readonly class="edita" type="text" id="docNumero" name="docNumero">	<br /> 
				 	<input readonly class="edita" type="text" id="telefone">	<br />
				 	<input readonly class="edita" type="text" id="email">	<br /> 
					<select readonly id="sexo">
						<option readonly class="edita" value="masculino">Masculino</option>
						<option readonly class="edita" value="feminino">Feminino</option>
					</select>	<br />
					<input readonly class="edita" type="text" name="dataNascimento" >	<br />
				</div>			
				<div id="dAbaixo">
					<input onclick="editavel" type="button" value="Editar"/> - 
					<input type="button" value="Excluir" class="bVermelho" />
					<input type="hidden" id="form" value="ProcuraClienteLogic">
					<input type="hidden" id="tipo" name="tipo" />
				</div>
			</form>
		<div id="fim">
			</div>
		</div>
		<div class="metadeDireita"> 
		</div>
	</div>
</body>
</html>
