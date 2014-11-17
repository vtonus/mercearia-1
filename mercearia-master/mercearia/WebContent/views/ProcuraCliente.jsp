
<div id="procli" style="position: relative;" class="panel panel-default">
	<div class="panel-heading " >
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Procura Cliente</h3>
		</div>
	</div>
	<br><br>
		<form id="meuForm" onSubmit="JavaScript:handleClick1()">
		Procurar: 
		<input type="text" id="palavraChave" autofocus >
		 &nbsp;&nbsp;&nbsp;Por:
		
		<select class="form-control" id="parametro" style="width: 200px; display: inline;">
			<option value="nome">Nome</option>
			<option value="cpf">CPF</option>
			<option value="telefone">Telefone</option>
		</select>
	
		<input type="submit" value="Buscar" class=" btn btn-primary">	<br /><br />
		</form>
		
		<div id="result" style="display:none;">
		
		</div>		
		<div class="resposta" >
	
		</div>	
		<div id="tabdados">
		
		</div>
		<div class="fundoq">
		</div>
		<div class="question">
	 </div>
</div>	
			
