
<div id="NovoProduto">
<span class="titulo"> Cadastro De Produto</span>
<form name="myForm" id="formulario" onSubmit="JavaScript:handleClick3()">
<table class="cad">

		<tr>	
			<td>	<label for="id">Codigo de Barras*:</label></td>
			<td>	
			<input required type="text" name="id" id="nome" autofocus>  </td>
		</tr>
		<tr>	
			<td>	<label for="nome">Descrição*:</label></td>
			<td>	
			<input required type="text" name="nome" id="id" autofocus />  </td>
		</tr>
			<tr>
			<td>	<label for="doc">valor</label></td>
			<td>	<input type="text" id="cpf" name="valor" > </td>
 			</tr>
 			<tr>
 			<td>	<label for="tel">Fabricante:</label></td>
 			<td>	<input id="Telefone" type="text" name="fabricante" min="8" max="14"></td>
			</tr>

			<tr>
			<td>	<label for="dtn">Estoque:</label></td>
			<td>     <input type="text" name="estoque"> <br ></td>
			</tr>	
			
					</table>
				
					<input class="confirmar"  type="submit" value="Confirmar" > </input>
	 				<div class="limpar" onclick="$('.cad input').val('');">Limpar</div>
 				</form>
 				
	</div>

