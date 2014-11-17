
<div id="NovoCliente" class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Cadastro De Clientes</h3>
		</div>
	</div>
	<div class="panel-body">
		<form name="myForm" id="formulario"
			onSubmit="JavaScript:handleClick()" class="form form-vertical">

			<div class="control-group">
				<label>Nome</label>
				<div class="controls">
					<input class="form-control" required type="text" name="nome"
						id="nome" autofocus>
				</div>
			</div>

			<div class="control-group">
				<label>CPF</label>
				<div class="controls">
					<input class="form-control" type="text"
						pattern="[0-9]{9}[A-Za-z0-9]{2}" id="cpf" name="doc">
				</div>
			</div>

			<div class="control-group">
				<label>Telefone</label>
				<div class="controls">
					<input class="form-control" id="Telefone" type="text"
						name="telefone" min="8" max="14">
				</div>
			</div>


			<div class="control-group">
				<label>Sexo:</label>
				<div class="controls">

					<select name="sexo" id="sexo" class="form-control">
						<option value="masculino">Masculino</option>
						<option value="feminino">Feminino</option>
					</select>
				</div>
			</div>


			<div class="control-group">
				<label>Data de Nascimento</label>
				<div class="controls">
					<input class="form-control" type="date" name="dtn">

				</div>
			</div>



			<div class="control-group">
				<label>Email</label>
				<div class="controls">
					<input class="form-control" id="email" type="email" name="email">
				</div>
			</div>

			<div class="control-group">
				<label>Endereco</label>
				<div class="controls">
					<input class="form-control" type="text" name="endereco" id="end">
				</div>
			</div>

			<div class="control-group">
				<label></label>
				<div class="controls">
					<input class="btn btn-primary" type="submit" class="confirmar"
						value="Confirmar"></input>
					<div class="btn btn-danger" onclick="$('.form-control').val('');">
						Limpar</input>
					</div>
				</div>
		</form>


	</div>

	</form>
</div>

