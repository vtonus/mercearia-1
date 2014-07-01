<html>
<head>
<script type="text/javascript"  src="../js/scripts.js"></script>
<script type="text/javascript">
	window.onload = function()
	{
		if ( "${cliente}" == "cliente") 
		{
			alert("Cliente cadastrado com sucesso!");
		}
	};
</script>
<link rel="stylesheet" type="text/css" href="../css/style.css">

<style>

#dEsquerda
{
	float:left;
	text-align:right;
	line-height: 40px;
}

#dDireita
{
	float:left;
	line-height: 43px;
}


.esquerda
{
	width:400px;
}

#dAbaixo
{
	line-height:50px;
}
#pAbaixo
{
	text-align:right;
}
</style>
<title>Novo cliente</title>
</head>
<body>
	<div class="titulo"><h1>Cadastro de um novo cliente:</h1></div>
	<div class="esquerda">
		<form name="myForm" action="Control" method="post">
			<div id="dEsquerda">			
			Nome*: <br>
			CPF:<br>
			Telefone:<br>
			Sexo:<br>
			Endereço:<br>
			Email:<br>
			Data de nascimento:<br>
			</div>
			<div id="dDireita">
				<input required type="text" name="nome" id="nome" autofocus> <br />
				<input type="text" pattern="[0-9]{9}[A-Za-z0-9]{2}" name="doc"> <br />
 				<input type="text" name="telefone" min="8" max="14"> <br />
				<select name="sexo"> 
					<option value="masculino">Masculino</option>
					<option value="feminino">Feminino </option>
 				</select><br />
 				<input type="text" name="endereco"> <br />
				<input type="email" name="email"> <br />
				<input type="date" name="dataNascimento" min="1900-01-01" max="2030-12-31"> <br />
			</div>
			<div id="dAbaixo">
 				<input type="hidden" name="logic" value="NovoClienteLogic">
				*Preenchimento obrigatório <br> 
 				<p id="pAbaixo">
 					<input type="submit" value="CONFIRMA" class="bVerde"> -
	 				<input type="button" value="Limpa formulário" class="bCinza">
 				</p>
 			</div>		
		</form>
	</div>
			
</body>
</html>