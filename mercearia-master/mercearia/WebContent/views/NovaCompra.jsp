
<div id="NovaCompra">
<span class="titulo">Nova Compra</span>
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
<div class="confirmar" onclick="confirmaCompra()"> Confirmar</div>
<div class="limpar" onclick="cancelaCompra()">Cancelar</div>
 	
 	
</div>		