<div id="procli">

	<span class="titulo">Procura Compra</span>
	<form id="meuForm" onSubmit="JavaScript:handleClick2()">
		<div style="max-width: 200px; float: left; margin-right:10px;">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">Filtrar por Dados</div>
				<div class="control-group">
					<label>Id:</label>
					<div class="controls">
						<input class="form-control" type="text" id="codigo">
					</div>
				</div>
				<div class="control-group">
					<label> Cliente:</label>
					<div class="controls">
						<input class="form-control" type="text" id="cliente">
					</div>

				</div>
				<div class="control-group">
					<label> Funcionario:</label>
					<div class="controls">
						<input class="form-control" type="text" id="func">
					</div>
				</div>

			</div>
		</div>

		<div style="max-width: 200px; float: left;clear:right;">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">Filtrar por data</div>

				<div class="control-group">
					<label> Data Inicial:</label>
					<div class="controls">
						<input class="form-control" type="date" id="dtini">
					</div>

				</div>
				<div class="control-group">
					<label> Data Final:</label>
					<div class="controls">
						<input class="form-control" type="date" id="dtfin">
					</div>
				</div>

			</div>
		</div>
		<input type="submit" class="buscarestilo compra btn btn-primary"
			value="Buscar" /> </br> </br>
	</form>

	<div id="result" style="display: none"></div>
	<div class="resposta"></div>
	<div id="tabdados" style="clear: both;"></div>
	<div class="fundoq"></div>
	<div class="question"></div>


	<div id="detalhes"></div>
</div>
