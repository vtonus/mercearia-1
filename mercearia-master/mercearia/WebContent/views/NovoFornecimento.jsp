<form action="Control" method="post">
<div class="titulo"><h1>Cadastro de um novo fornecimento:</h1></div>

<div class="esquerda">
	<table>
	
	<tr>	
			<td>	<label for="nome">Fornecedor*:</label></td>
			<td>	<input type="text" name="fornecedor" style="width:300px;" autofocus> <br /> </td>
		</tr>
			<tr>
			<td>	<label for="doc">Data*:</label></td>
			<td>		<input type="date" name="data" class="data"><br /></td>
 			</tr>
 			<tr>
 			<td>	<label for="tel">Hora*:</label></td>
 			<td>	<input type="time" name="hora"><br /></td>
			</tr>
			<tr>
			<td>	<label for="sex">Comentário:</label></td>
			<td>	<textarea name="comentario" rows="3" cols="30" maxlength="130"></textarea><br /></td>

			
		</tr>
		</table>
			<img onclick="adcProdutoForn()" src="../images/exclui.png">
			
				
 </div>
 
<table border="1" class="tabelaForn">
  	<tr>
	    <th>Código</th>
	    <th>Produto</th>
	    <th>Unid.</th>
	    <th>Qtd.</th>
	    <th>Total</th>
	    <th></th>
  	</tr>
</table>

 

 <input type="hidden" name="logic" value="NovoFornecimentoLogic"><br />
 <input type="submit" value="Confirma"> - 
 <input type="button" value="Limpa">