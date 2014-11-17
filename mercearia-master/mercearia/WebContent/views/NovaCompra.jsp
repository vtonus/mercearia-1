<div class="fundoq"
	style="position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: #000; z-index: 9; opacity: 0.4; display: none">
</div>
<div class="questao panel panel-default"
	style="position: fixed; top: 29; width: 400px; height: 475px; z-index: 10; margin-left: 50%; left: -150px; border-radius: 10px; display: none">
	<div class="panel-heading">Confirmar Compra</div>
	<div class="panel-body">
		<div
			style="width: 86px; height: 99px; margin: auto; position: relative; top: -12px;">
			<div class="checkbox">
				<input id="metod1" checked onclick="metodopag(1)" type="radio"
					name="metodoPag" value="dinheiro" />Dinheiro
			</div>
			<div class="checkbox">
				<input id="metod2" onclick="metodopag(0)" type="radio"
					name="metodoPag" value="card">Cartão<br>
			</div>
			<div class="checkbox">
				<input id="metod3" onclick="metodopag(2)" type="radio"
					name="metodoPag" value="fiado">Prazo<br>
			</div>

		</div>



		<span class="concmptxt">PAGO:</span><br /> <span class="rs">R$:</span>
		<input	id="pagado" type="text" class="confcmp form-control"  name="quantopagou"
			onkeyup="mascara(this);calculo()" style="color: #00aa00" /> 
		<span style="display: none;" class="concmptxt ">DESCONTO:</span>
		 <span	style="display: none;" class="RS" style="color:#ff0000">R$:</span>
		 <input	style="display: none;" id="descount" type="text" class="confcmp form-control"
			name="quantopagou" style="color:red"
			onkeyup="mascara(this);calculo()" /><span class="concmptxt">TOTAL:</span><br />
		<span class="rs">R$:</span><input id="total" type="text"
			class="confcmp form-control" name="quantopagou" style="color: #00aa00"
			onkeyup="mascara(this);calculo()" /> <span class="concmptxt">TROCO:</span><br>
		<span class="rs">R$:</span><input id="trocado" type="text"
			class="confcmp form-control" name="quantopagou" style="color: #00aa00"
			onkeyup="mascara(this);calculo()" />

		<div style="width: 80px; font-size: 12px; top: 10px; left: 77px !important; position: relative;"
			class="confirmar btn btn-primary" onclick="confirmaCompra()">Confirmar</div>
		<div style="width: 80px; font-size: 12px; top: 4px;left: 106px !important; position: relative; " class="limpar btn btn-danger"
			onclick="cancelapergunta()">Cancelar</div>
	</div>
</div>
<span class="titulo">Nova Compra</span>
<div id="NovaCompra"
	style="position: relative; top: -8px; height: 595px;">

	<label class="labelsdesc form-control" for="descricao">Descrição</label>
	<input class="desc form-control" type="text" name="descricao" readonly>
	<label class="labelscod form-control" for="codigo">Código de
		Barras</label> <input class="cod form-control" type="text" name="codigo"
		onkeyup="juntaCompra()"> <label class="labelsqtd form-control"
		for="qtd">Quantidade</label> <input class="qtd form-control"
		type="text" name="qtd"> <label class="labelsvlr form-control"
		for="valor">Valor Unitário</label> <input class="vlr form-control"
		type="text" name="valor">

	<div class="total">
		<table
			class="totaltab table table-bordered table-condensed table-hover table-striped">

		</table>
	</div>
	<div class="produtos" style='overflow: auto'>
		<table
			class="comprado table table-bordered table-condensed table-hover table-striped">

		</table>
	</div>

	<span class='retornando'></span>
	<div class="confirmar btn btn-primary" onclick="pagamentoCompra()">
		Confirmar</div>
	<div class="limpar btn btn-danger" onclick="cancelaCompra()">Cancelar</div>


	<div class="fundoq"></div>
	<div class="question"></div>
</div>
