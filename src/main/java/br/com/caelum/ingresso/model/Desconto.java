package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

public interface Desconto {

	public BigDecimal aplicarValorSobre(BigDecimal precoOriginal);

	String getDescricao();

}
