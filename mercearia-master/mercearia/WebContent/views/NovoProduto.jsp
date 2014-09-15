
<div id="NovoProduto">
<span class="titulo"> CADASTRO DE Produto</span>
<form name="myForm" id="formulario">
<table class="cad">

		<tr>	
			<td>	<label for="id">Codigo de Barras*:</label></td>
			<td>	
			<input required type="text" name="id" id="nome" autofocus value="4" >  </td>
		</tr>
		<tr>	
			<td>	<label for="nome">Descrição*:</label></td>
			<td>	
			<input required type="text" name="nome" id="id" autofocus />  </td>
		</tr>
			<tr>
			<td>	<label for="doc">valor</label></td>
			<td>	<input type="text" pattern="[0-9]{9}[A-Za-z0-9]{2}" id="cpf" name="valor" > </td>
 			</tr>
 			<tr>
 			<td>	<label for="tel">Fabricante:</label></td>
 			<td>	<input id="Telefone" type="text" name="fabricante" min="8" max="14"></td>
			</tr>
			<tr>
 			<td>	<label for="end">Quantidade:</label><br></td>
 			<td>	<input type="text" name="qtd" id="end"> </td>
 			</tr>
 			<tr>
 			<td>	<label for="email">Validade</label></td>
			<td>	<input id="email" type="date" name="validade"></td>
			</tr>
			<tr>
			<td>	<label for="dtn">Estoque:</label></td>
			<td>     <input type="text" name="estoque"> <br ></td>
			</tr>	
			
					</table>
				</form>
					<div class="confirmar" onclick="novoProdutofaz()"> Confirmar</div>
	 				<div class="limpar" onclick="$('.cad input').val('');">Limpar</input>
 				</p>
	</div>

