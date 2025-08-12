package Model;

public class Dinossauro {
    private int id_Dinossauro;
    private String nome_Dinossauro;
    private String especie_Dinossauro;
    private String dieta_Dinossauro;
    private String status_Dinossauro;
    private int idade_Dinossauro;
    private int idade_Estimada_Dinossauro;

    public Dinossauro(int id_Dinossauro, String nome_Dinossauro, String especie_Dinossauro, String dieta_Dinossauro, String status_Dinossauro, int idade_Dinossauro, int idade_Estimada_Dinossauro) {
        this.id_Dinossauro = id_Dinossauro;
        this.nome_Dinossauro = nome_Dinossauro;
        this.especie_Dinossauro = especie_Dinossauro;
        this.dieta_Dinossauro = dieta_Dinossauro;
        this.status_Dinossauro = status_Dinossauro;
        this.idade_Dinossauro = idade_Dinossauro;
        this.idade_Estimada_Dinossauro = idade_Estimada_Dinossauro;
    }

    public Dinossauro(String nome_Dinossauro, String especie_Dinossauro, String dieta_Dinossauro, int idade_Estimada_Dinossauro, int idade_Dinossauro, String status_Dinossauro) {
        this.nome_Dinossauro = nome_Dinossauro;
        this.especie_Dinossauro = especie_Dinossauro;
        this.dieta_Dinossauro = dieta_Dinossauro;
        this.status_Dinossauro = status_Dinossauro;
        this.idade_Dinossauro = idade_Dinossauro;
        this.idade_Estimada_Dinossauro = idade_Estimada_Dinossauro;
    }

    public int getId_Dinossauro() {
        return id_Dinossauro;
    }

    public String getNome_Dinossauro() {
        return nome_Dinossauro;
    }

    public String getEspecie_Dinossauro() {
        return especie_Dinossauro;
    }

    public int getIdade_Dinossauro() {
        return idade_Dinossauro;
    }

    public int getIdade_Estimada_Dinossauro() {
        return idade_Estimada_Dinossauro;
    }

    public String getStatus_Dinossauro() {
        return status_Dinossauro;
    }

    public String getDieta_Dinossauro() {
        return dieta_Dinossauro;
    }
}
