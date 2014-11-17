<span class="titulo"> Procura Produtos</span>
<div id="procli">

	<form id="meuForm" onSubmit="JavaScript:handleClick4()">
	<div style="max-width: 200px; float: left; margin-right:10px;">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">Filtrar por Dados</div>
				<div class="control-group">
					<label>Código do Produto: </label>
					<div class="controls">
						<input class="form-control" type="text" id="codigo" autofocus>
					</div>
				</div>
				<div class="control-group">
					<label> Nome:</label>
					<div class="controls">
						<input class="form-control" type="text" id="nome">
					</div>

				</div>
				<div class="control-group">
					<label> Fabricante:</label>
					<div class="controls">
						<input class="form-control" type="text" id="fabricante">
					</div>
				</div>

			</div>
		</div>
	
<div style="max-width: 200px; float: left;clear:right;">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">Filtrar por data</div>

				<div class="control-group">
					<label> Valor min:</label>
					<div class="controls">
						<input class="form-control" type="text" id="valorMin">
					</div>

				</div>
				<div class="control-group">
					<label>Valor max:</label>
					<div class="controls">
						<input class="form-control" type="text" id="valorMax">
					</div>
				</div>

			</div>
		</div>
			
		
		
		<br> <input type="submit" class="buscarestilo" value="Buscar">
		<br />
		<br />

		<div id="result" style="display: none;"></div>
		<div class="resposta"></div>
		<div id="tabdados" style="clear: both;"></div>
		<div class="fundoq"></div>
		<div class="question"></div>
</div>

