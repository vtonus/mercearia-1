
<div id="NovoCliente" class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Cadastro De Funcionários</h3>
		</div>
	</div>
	<div class="panel-body">
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick8()">

	<div class="control-group">
				<label>NOme</label>
				<div class="controls">
					<input class="form-control" required type="text" name="nome" id="nome" autofocus>
				</div>
			</div>
		<div class="control-group">
				<label>CPF:</label>
				<div class="controls">
					<input class="form-control" type="text" pattern="[0-9]{9}[A-Za-z0-9]{2}" id="cpf" name="doc">
				</div>
			</div>		
			
			
			<div class="control-group">
				<label>Usuario:</label>
				<div class="controls">
					<input class="form-control"  id="Telefone" type="text" name="usuario" min="8" max="14">
				</div>
			</div>	
			
			<div class="control-group">
				<label>Senha:</label>
				<div class="controls">
					<input class="form-control" id="Telefone" type="password" name="senha" min="8" max="14">
				</div>
			</div>	
			
			
			<div class="control-group">
				<label>Telefone:</label>
				<div class="controls">
					<input class="form-control" id="Telefone" type="text" name="telefone" min="8" max="14">
				</div>
			</div>
			
			<div class="control-group">
				<label>Email:</label>
				<div class="controls">
					<input class="form-control" id="email" type="email" name="email">
				</div>
			</div>
			
				<div class="control-group">
				<label>Data de nascimento:</label>
				<div class="controls">
					<input class="form-control" type="date" name="dtn">
				</div>
			</div>
	
					<input type="submit"  value="confirmar" class="confirmar btn btn-primary" onclick="()"></input>
	 				<div class="limpar btn btn-danger" onclick="$('.cad input').val('');">Limpar</input>
	 					</form>

	</div></div>
