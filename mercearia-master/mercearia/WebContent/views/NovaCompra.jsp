<div class="fundoq" style="position: fixed;top: 0;left: 0;width: 100%;height: 100%;background-color: #000;z-index: 9;
opacity: 0.4; display:none">
</div>
<div class="questao" style="position: fixed;
top: 29;
width: 400px;
height: 600px;
background-color: #ddd;
z-index: 10;
display: block;
margin-left: 50%;
left: -150px;
border-radius: 10px;
display:none">

<div style="width: 86px;height: 100px;margin: auto;position: relative;top: 18px;">
<input checked type="radio" name="pagamento" value="dinhiro" style="-webkit-box-shadow: none;">Dinheiro<br>
<input type="radio" name="pagamento" value="card" style="-webkit-box-shadow: none;">Cartão<br>
<input type="radio" name="pagamento" value="fiado" style="-webkit-box-shadow: none;">Prazo<br>
</div>



<span class="concmptxt">PAGO:</span><br/>
<span class="rs">R$:</span><input id="pagado" type="text" class="confcmp" name="quantopagou" onkeyup="mascara(this);calculo()" style="color:#00aa00"/>
<span class="concmptxt">DESCONTO:</span><br>
<span class="RS" style="color:#ff0000">R$:</span><input  id="descount" type="text" class="confcmp" name="quantopagou" style="color:red" onkeyup="mascara(this);calculo()"/>
<span class="concmptxt">TOTAL:</span><br/>
<span class="RS">R$:</span><input id="total" type="text" class="confcmp" name="quantopagou" style="color:#00aa00" onkeyup="mascara(this);calculo()"/>
<span class="concmptxt">TROCO:</span><br>
<span class="RS">R$:</span><input id="trocado" type="text" class="confcmp" name="quantopagou" style="color:#00aa00" onkeyup="mascara(this);calculo()"/>

<div style="width: 80px;font-size: 12px;top: 10px;" class="confirmar" onclick="confirmaCompra()"> Confirmar</div>
	 				<div style="width: 80px; font-size: 12px;top: -24px;" class="limpar" onclick="cancelapergunta()">Cancelar</div>
</div>
<span class="titulo">Nova Compra</span>
<div id="NovaCompra" style="position: relative; top: -8px;">

  <label class="labelsdesc"  for="descricao">Descrição</label>
<input class="desc" type="text" name="descricao" readonly >
  <label  class="labelscod"  for="codigo" >Código de Barras</label>
<input class="cod" type="text" name="codigo" onkeyup="juntaCompra()">
  <label class="labelsqtd"  for="qtd">Quantidade</label>
<input class="qtd" type="text" name="qtd" >
  <label class="labelsvlr"  for="valor">Valor Unitário</label>
<input class="vlr" type="text" name="valor" >

<div class="total">
 <table class="totaltab">
   
   	</table>
</div>
<div class="produtos" style='overflow:auto'>
   <table class="comprado">
   
   	</table>
</div>

<span class='retornando'></span>
<div class="confirmar" onclick="pagamentoCompra()"> Confirmar</div>
<div class="limpar" onclick="cancelaCompra()">Cancelar</div>
 	
 
<div class="fundoq">
		</div>
		<div class="question">
	 </div>
</div>	
 		