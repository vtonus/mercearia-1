
<div id="NovoProduto" class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Cadastro De Produto</h3>
		</div>
		</div>
		<div class="panel-body">
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick3()">
	<div class="control-group">
				<label>Codigo de Barras*:</label>
				<div class="controls">
					<input class="form-control" required type="text" name="id" id="nome" autofocus>
				</div>
			</div>

<div class="control-group">
				<label>Descrição*:</label>
				<div class="controls">
					<input class="form-control" required type="text" name="nome" id="id" autofocus>
				</div>
			</div>

<div class="control-group">
				<label>Valor:</label>
				<div class="controls">
					<input class="form-control" type="text" id="cpf" name="valor">
				</div>
			</div>

<div class="control-group">
				<label>Estoque:</label>
				<div class="controls">
					<input class="form-control" type="text" name="estoque">
				</div>
			</div>



			
 				
	</div>
	<input class=" btn btn-primary"  type="submit" value="Confirmar" > </input>
	 				<div class=" btn btn-danger" onclick="$('.cad input').val('');">Limpar</div>
 				</form>
</div>