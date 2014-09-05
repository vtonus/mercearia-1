
<div id="NovaCompra">
<span class="titulo">Nova Compra</span>
  <label class="labelsdesc"  for="descricao">Descrição</label>
<input class="desc" type="text" name="descricao" >
  <label  class="labelscod"  for="codigo">Código de Barras</label>
<input class="cod" type="text" name="codigo" >
  <label class="labelsqtd"  for="qtd">Quantidade</label>
<input class="qtd" type="text" name="qtd" >
  <label class="labelsvlr"  for="valor">Valor Unitário</label>
<input class="vlr" type="text" name="valor" >
  <label class="labelssb" for="subtotal">Sub-Total</label>
<input class="sb" type="text" name="subtotal" >

<div class="total">

</div>
<div class="produtos">
   <table class="comprado"></table>
</div>


<div class="confirmar" onclick=""> Confirmar</div>
<div class="limpar" onclick="$('.cad input').val('');">Cancelar</div>
 				
</div>