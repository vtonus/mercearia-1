<div id="relatorio">
	<div class="menu">
		<ul>
			<li onclick='mudaview1();'>Diario |</li>
			<li onclick="mudaview2();">Por periodo |</li>
			<li class="botaorelatorio">Por Produto |</li>
			<li class="botaorelatorio">Perda</li>
		</ul>
	</div>
	<div class="diario some">
		<div class="escolhas">
			<div class="escolha">
				<form id="formulario" onSubmit="JavaScript:handleClick10()">
					<table>
						<tr>
							<td>Coloque uma data para gerar o Relátorio</td>
						</tr>
						<tr>
							<td><input name="dia" style="width: 100%" type="date" /></td>
						</tr>
					</table>
					<input type="submit" class="confirmar procurarrela"
						value="Procurar"> </input>
				</form>
			</div>
			<div class="dados">

				<div class="toprod"></div>
				<div class="cmpmes">
					<div id="chart_div" style="width: 852px; height: 306px;"></div>


				</div>

				<div class="cmpmes2">
					<div id="chart_div2" style="width: 852px; height: 252px;"></div>


				</div>


				<div class="mediamensal"></div>
				<div class="caixa"></div>



			</div>

			<div class="cmphoras"></div>

		</div>
	</div>
	<div class="periodo some">
		<div class="top">
			<form id="formulario2" onSubmit="JavaScript:handleClick11()">
				<table>
					<tr>
						<td>Coloque uma data para gerar o Relátorio</td>
					</tr>
					<tr>
						<td>mes 1:<input name="mes1" style="width: 100%" type="text" /></td></tr>
					<tr>	<td>mes 2:<input name="mes2" style="width: 100%" type="text" /></td></tr>
					<tr>	<td>ano 1:<input name="ano1" style="width: 100%" type="text" /></td></tr>
					<tr>	<td>ano 2:<input name="ano2" style="width: 100%" type="text" /></td>
					</tr>
				</table>
				<input type="submit" class="confirmar procurarrela" value="Procurar">
				</input>
			</form>



		</div>
		<div class="venda"></div>
		<div class="total1"></div>
		<div class="fornecedor"></div>
		<div class="gastoforn"></div>
		<div class="total2"></div>
	</div>


	<div class="cmphoras"></div>


</div>


</div>

<div id="result"></div>


















</div>