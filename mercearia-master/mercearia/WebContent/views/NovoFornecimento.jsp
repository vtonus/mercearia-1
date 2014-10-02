
<div class="titulo">
	Cadastro de um novo fornecimento:
</div>

<div class="esquerda">
	<table>
		<tr>
			<td><label for="nome">Fornecedor*:</label></td>
			<td><input id="fornnome" onkeyup="procuraNomedoFornecedor()" type="text" name="fornecedor" style="width: 300px;"
				autofocus> <br />
				<div id="resultForne" style="position: absolute;background: #fff;width: 300px;top: 72px;"></div>
				</td>


			<td><label for="doc">Data*:</label></td>
			<td><input type="date" name="data" class="data" width="50px"><br /></td>
			<td><label for="tel">Hora*:</label></td>
			<td><input type="time" name="hora"><br /></td>
		</tr>
		<tr>
			<td><label for="sex">Comentário:</label></td>
			<td><textarea id="comentarioforn" name="comentario" rows="3" cols="30"
					maxlength="130">comentario</textarea><br /></td>


		</tr>
	</table>


	<table>
		<tr>
			<th>Código</th>
			<th>Produto</th>
			<th>Valor Unid.</th>
			<th>Qtd.</th>
			<th>Valor</th>
			<th></th>
		</tr>
		<tr>
			<td><input id="cod" type="text" name="hora" /></td>
			<td><input id="prod" type="text" name="hora" /></td>
			<td><input id="unid" type="text" name="hora" /></td>
			<td><input id="qtd" type="text" name="hora" /></td>
			<td><input id="vlr" type="text" name="hora" /></td>
			<td><img onclick="adcProdutoForn()"
				src="../images/adicionapedido.png" /></td>
		</tr>



	</table>

</div>
<div id="tabelapedcab">
<table>
		<tr>
			<td>Código</td>
			<td>Produto</td>
			<td>Unid.</td>
			<td>Qtd.</td>
			<td>Valor</td>
		
		</tr>
		</table>
		</div>
<div id="tabelaped">
	<table border="1" class="tabelaForn">
		

	</table>
</div>



<input type="hidden" name="logic" value="NovoFornecimentoLogic">
<br />
<input type="button" onclick='confirmaPedido()'>
-
<input type="button" value="Limpa">
<span style="display:none" id="resultados"></span>