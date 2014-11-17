<div id="fornecimentoestilo">
	<div class="panel-heading">
		<div class="panel-title">
			<i class="glyphicon glyphicon-wrench pull-right"></i>
			<h3 class="panel-title">Cadastro De Pedido</h3>
		</div>
	</div>

	<div class="esquerda">
		<div class="panel-body">
			<div class="control-group col-md-5">
				<label>Fornecedor*:</label>
				<div class="controls">
					<input class="form-control" id="fornnome"
						onkeyup="procuraNomedoFornecedor()" type="text" name="fornecedor"
						autofocus>
				</div>
			</div>




			<div class="control-group col-md-3">
				<label>Data*:</label>
				<div class="controls">
					<input class="data form-control" type="date" name="data">
				</div>
			</div>

			<div class="control-group col-md-2">
				<label>Hora*:</label>
				<div class="controls">
					<input class="data form-control" type="time" name="hora">
				</div>
			</div>
			<div class="control-group col-md-12">
				<label>Comentário*:</label>
				<div class="controls">
					<textarea class="form-control" id="comentarioforn"
						name="comentario" rows="3" cols="30" maxlength="130"
						placehold="comentário"></textarea>
				</div>
			</div>

			<div class="control-group col-md-12">
				<table id="fornecimentoestilo2"
					class="table table-striped responsive-table">
					<tr>
						<th>Código*</th>
						<th>Produto*</th>
						<th>Valor Unid.*</th>
						<th>Qtd.*</th>
						<th>Valor*</th>
						<th></th>
					</tr>
					<tr>
						<td><input id="cod" type="text" style="width: 100%;"
							name="hora" onkeyup="buscacodigoforn();" /></td>
						<td><input id="prod" type="text" style="width: 100%;"
							name="hora" readonly /></td>
						<td><input id="unid" type="text" style="width: 100%;"
							name="hora" /></td>
						<td><input id="qtd" type="text" style="width: 100%;"
							name="hora" onkeyup="adcProdutoFornqtd()" /></td>
						<td><input id="vlr" type="text" style="width: 100%;"
							name="hora" /></td>
						<td><span class="glyphicon glyphicon-plus"
							onclick="adcProdutoForn()" style="cursor: pointer"></span></td>
					</tr>



				</table>
			</div>
			<div class="control-group col-md-12">
				<div id="tabelapedcab">
					<table class="table table-striped responsive-table">
						<tr>
							<th>Código</th>
							<th>Produto</th>
							<th>Unid.</th>
							<th>Qtd.</th>
							<th>Valor</th>

						</tr>
					</table>
				</div>
				<div id="tabelaped">
					<table border="0" class="tabelaForn table table-striped responsive-table"
						style="border-collapse: collapse;">


					</table>



				</div>


			</div>
		</div>

		<br>
		<br>
	</div>
	<table id="totaltabela"></table>


	<input type="hidden" name="logic" value="NovoFornecimentoLogic">
	<br /> 
	<div class="control-group col-md-7">
	<input type="button" onclick='confirmaPedido()'
		class="confirmar btn btn-primary" value="Confirmar">
	<div class="retornando" style="display: none"></div>
	<div type="button " onclick="limpapedi()" class="limpar btn btn-danger">
		Limpa
		</div>
		</div>
		<div>
			<span style="display: none" id="resultados"></span>
		</div>