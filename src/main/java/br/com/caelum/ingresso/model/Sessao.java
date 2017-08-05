package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {

	/**
	 * @deprecated hibernate only
	 */
	public Sessao() {

	}

	public Sessao(LocalTime horario, Sala sala, Filme filme) {

		this.horario = horario;
		this.sala = sala;
		this.filme = filme;
		this.preco = filme.getPreco().add(sala.getPreco());
	}

	@Id
	@GeneratedValue
	private Integer id;

	private LocalTime horario;

	private BigDecimal preco;

	@ManyToOne
	private Sala sala;

	@ManyToOne
	private Filme filme;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public LocalTime getHorarioTermino() {
		return this.horario.plus(filme.getDuracao().toMinutes(), ChronoUnit.MINUTES);
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}
