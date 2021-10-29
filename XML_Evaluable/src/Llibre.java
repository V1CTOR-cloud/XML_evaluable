
public class Llibre { // clase libro, dos constructores
	//para poder isntanciarlo sin introducir sus parametros
	private String id, titulo, autor, anyo, editorial, paginas;
	
	public Llibre() {
		
	}
	// segundo constructor con sus parametros
	public Llibre(String id, String titulo, String autor, String anyo, String editorial, String paginas) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editorial = editorial;
		this.paginas = paginas;
	}
		// getters para acceder desde otras clases
	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getAnyo() {
		return anyo;
	}

	public String getEditorial() {
		return editorial;
	}

	public String getPaginas() {
		return paginas;
	}
}
