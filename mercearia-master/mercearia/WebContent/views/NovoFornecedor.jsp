<div id="NovoCliente" class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Cadastro De Fornecedor</h3>
		</div>
	</div>
	<div class="panel-body">
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick6()">

<div class="control-group">
				<label>Name</label>
				<div class="controls">
					<input class="form-control" required type="text" name="nome"
						id="nome" autofocus>
				</div>
			</div>
			
			
			<div class="control-group">
				<label>CNPJ:</label>
				<div class="controls">
					<input class="form-control" type="text"   id="cpf" name="CNPJ" >
				</div>
			</div>


	
			<div class="control-group">
				<label>Telefone:</label>
				<div class="controls">
					<input class="form-control" id="Telefone" type="text" name="telefone" min="8" max="14">
				</div>
			</div>

<div class="control-group">
				<label>Endereço:</label>
				<div class="controls">
					<input class="form-control" type="text" name="endereco" id="end">
				</div>
			</div>

<div class="control-group">
				<label>Email:</label>
				<div class="controls">
					<input class="form-control" id="email" type="email" name="email">
				</div>
			</div>
</div>
				
					<input type="submit" class="confirmar btn btn-primary" value="confirmar"></input>
	 				<div class="btn btn-danger" onclick="$('.form-control').val('');">Limpar</div>
	 				

 				</form>
	</div>
