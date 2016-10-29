package br.cefetmg.games.screens;

import br.cefetmg.games.Config;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import br.cefetmg.games.Rank;
import br.cefetmg.games.minigames.util.ActualMenuScreen;
import br.cefetmg.games.minigames.util.GameOption;
import br.cefetmg.games.minigames.util.Score;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import java.util.ArrayList;

//imports Carlos e Bruno

import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.scenes.scene2d.Actor; 
import com.badlogic.gdx.scenes.scene2d.Stage; 
import com.badlogic.gdx.scenes.scene2d.ui.Table; 
import com.badlogic.gdx.scenes.scene2d.ui.Button; 
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton; 
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener; 
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable; 

/**
 * Uma tela de Menu Principal do jogo.
 *
 * @author Flávio Coutinho - fegemo <coutinho@decom.cefetmg.br>
 */

public class MenuScreen extends BaseScreen {
    
    //****linhas apagadas by Bruno e Carlos
    //private static final int NUMBER_OF_TILED_BACKGROUND_TEXTURE = 7;
    //private TextureRegion background;
    //****fim
    
    private final Sound introMusic;
    
    //****linhas apagadas by Bruno e Carlos
    //***Alterações para o modo survival by Lindley and Lucas Viana
    //private final Sprite normalButton, survivalButton, rankingButton;
    //****fim
    private ActualMenuScreen actualScreen;
    private final Rank rank;
    //***Fim do bloco de alterações
    
    //****Textures e stage by Bruno e Carlos
    private TextureRegion background,buttonIniciarTexture,buttonCreditosTexture,buttonSairTexture,
                buttonSurvivalTexture,buttonRankingTexture; 
    private Stage stage; 
    private Button buttonIniciar, buttonSair, buttonCreditos,buttonSurvival,buttonRanking;
    //****fim
    
    /**
     * Cria uma nova tela de menu.
     *
     * @param game o jogo dono desta tela.
     * @param previous a tela anterior a esta.
     */
    public MenuScreen(Game game, BaseScreen previous) {
        super(game, previous);
        
        //Define a música tema
        introMusic = Gdx.audio.newSound(Gdx.files.internal("sounds/menu.mp3"));
        
        //****linhas apagadas by Bruno e Carlos
        //***Alterações para o modo survival by Lindley and Lucas Viana
        //Carrega texturas para os botões do menu
        //normalButton = new Sprite(new Texture("buttons_menu/Normal.png"));
        //survivalButton = new Sprite(new Texture("buttons_menu/Survival.png"));
        //rankingButton = new Sprite(new Texture("buttons_menu/Ranking.png"));

        //Define as posições dos botões
        //normalButton.setPosition(40 * viewport.getWorldWidth() / 200.0f, viewport.getWorldHeight() / 2.5f);
        //survivalButton.setPosition(25 + 80 * viewport.getWorldWidth() / 200.0f, viewport.getWorldHeight() / 2.5f);
        //rankingButton.setPosition(50 + 120 * viewport.getWorldWidth() / 200.0f, viewport.getWorldHeight() / 2.5f);
        //****fim
        //Inicializa tela
        actualScreen = ActualMenuScreen.MENU;
        rank = new Rank();
        //***Fim do bloco de alterações
    }

    /**
     * Configura parâmetros da tela e instancia objetos.
     */
    @Override
    public void show() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        introMusic.play();
        /****Linhas apagadas by Bruno e Carlos
        // instancia a textura e a região de textura (usada para repetir)
        background = new TextureRegion(new Texture("menu-background.png"));
        // configura a textura para repetir caso ela ocupe menos espaço que o
        // espaço disponível
        background.getTexture().setWrap(
                Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        // define a largura da região de desenho de forma que ela seja repetida
        // um número de vezes igual a NUMBER_OF_TILED_BACKGROUND_TEXTURE 
        background.setRegionWidth(
                background.getTexture().getWidth()
                * NUMBER_OF_TILED_BACKGROUND_TEXTURE);
        // idem para altura, porém será repetida um número de vezes igual a 
        // NUMBER_OF_TILED_BACKGROUND_TEXTURE * razãoDeAspecto
        background.setRegionHeight(
                (int) (background.getTexture().getHeight()
                * NUMBER_OF_TILED_BACKGROUND_TEXTURE
                / Config.DESIRED_ASPECT_RATIO));
        fim****/
        background = new TextureRegion(new Texture("menu_background_m.jpg"));
        
        //****Instanciando Menu by Bruno e Carlos
        stage = new Stage(); 
        
	// Create a table that fills the screen. Everything else will go inside this table. 
        Table table = new Table(); 
        table.align(1); 
        table.padBottom(100); 
        table.setFillParent(true); 

        buttonIniciarTexture = new TextureRegion(new Texture("buttons_menu/button_iniciar.png")); 
        buttonIniciar = new ImageButton(new TextureRegionDrawable(buttonIniciarTexture)); 
        table.add(buttonIniciar);
        
        buttonSurvivalTexture = new TextureRegion(new Texture("buttons_menu/button_survival.png")); 
        buttonSurvival = new ImageButton(new TextureRegionDrawable(buttonSurvivalTexture)); 
        table.add(buttonSurvival);
        
        buttonRankingTexture = new TextureRegion(new Texture("buttons_menu/button_ranking.png")); 
        buttonRanking = new ImageButton(new TextureRegionDrawable(buttonRankingTexture)); 
        table.add(buttonRanking);
        table.row();

        buttonCreditosTexture = new TextureRegion(new Texture("buttons_menu/button_creditos.png")); 
        buttonCreditos = new ImageButton(new TextureRegionDrawable(buttonCreditosTexture)); 
        table.add(buttonCreditos);  

        buttonSairTexture = new TextureRegion(new Texture("buttons_menu/button_sair.png")); 
        buttonSair = new ImageButton(new TextureRegionDrawable(buttonSairTexture)); 
        table.add(buttonSair);
        
        buttonIniciar.addListener(new ChangeListener() { 
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                introMusic.stop();
                navigateToMicroGameScreen(GameOption.NORMAL); 
            } 
        }); 
        
        buttonSurvival.addListener(new ChangeListener() { 
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                introMusic.stop();
                navigateToMicroGameScreen(GameOption.SURVIVAL); 
            } 
        });
        
        buttonRanking.addListener(new ChangeListener() { 
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                actualScreen = ActualMenuScreen.RANKING;
                navigateToRankingScreen();
            } 
        });
        
        buttonCreditos.addListener(new ChangeListener() { 
            public void changed (ChangeListener.ChangeEvent event, Actor actor) { 
                    //TODO 
            } 
        }); 

        buttonSair.addListener(new ChangeListener() { 
            public void changed (ChangeListener.ChangeEvent event, Actor actor) { 
                Gdx.app.exit(); 
            } 
        }); 

        stage.addActor(table); 
        Gdx.input.setInputProcessor(stage);
        //****fim
    }

    /**
     * Recebe <em>input</em> do jogador.
     */
    @Override
    public void handleInput() {
        /**
         * Tratamento do click do mouse :: Lindley e Lucas Viana
         */
        
        /****linhas apagadas by Bruno e Carlos
        Vector2 click = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        viewport.unproject(click);        
        
        // se qualquer interação é feita (teclado, mouse pressionado, tecla
        // tocada), navega para a próxima tela (de jogo)
        if (Gdx.input.justTouched()) {
            if (actualScreen == ActualMenuScreen.MENU) {
                if (normalButton.getBoundingRectangle().contains(click)) {
                    introMusic.stop();
                    navigateToMicroGameScreen(GameOption.NORMAL);
                } else if (survivalButton.getBoundingRectangle().contains(click)) {
                    introMusic.stop();
                    navigateToMicroGameScreen(GameOption.SURVIVAL);
                } else if (rankingButton.getBoundingRectangle().contains(click)) {
                    actualScreen = ActualMenuScreen.RANKING;
                }
            } else {
                actualScreen = ActualMenuScreen.MENU;
            }
        }
        fim****/
    }
    
    /**
     * Atualiza a lógica da tela.
     *
     * @param dt Tempo desde a última atualização.
     */
    @Override
    public void update(float dt) {
        float speed = dt * 0.25f;
        //background.scroll(speed, -speed);
    }

    /**
     * Desenha o conteúdo da tela de Menu.
     */
    @Override
    public void draw() {        
        batch.begin();

        batch.draw(background, 0, 0,
                viewport.getWorldWidth(),
                viewport.getWorldHeight());
        //***Alterações para o modo survival by Lindley and Lucas Viana
        //Desenhar ranking ou opcaos de jogo
        if (actualScreen == ActualMenuScreen.MENU) {
            /****linhas apagadas by Bruno e Carlos
            drawCenterAlignedText("Selecione o modo de jogo",
                    1f, viewport.getWorldHeight() * 0.35f);

            //Desenha botões;
            normalButton.draw(this.batch);
            survivalButton.draw(this.batch);
            rankingButton.draw(this.batch);
            fim****/
            drawCenterAlignedText("Pressione qualquer tecla para jogar", 
                1f, viewport.getWorldHeight() * 0.35f); 
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); 
            stage.draw(); 
        } else {
            ArrayList<Score> ranking = rank.getRanking();
            for (int i = 0; i < ranking.size(); ++i) {
                drawCenterAlignedText(ranking.get(i).getName()
                        + " .......... " + ranking.get(i).getGames(),
                        1.0f, viewport.getWorldHeight() - 50f * (i + 1));
            }
            
             
        }
        /**/
        
        batch.end();
    }

    /**
     * Navega para a tela de jogo.
     */
    private void navigateToMicroGameScreen(final GameOption option) {
        transitionState = states.fadeOut;
        Timer.schedule(new Task() {
            @Override
            public void run() {
                transitionState = states.doNothing;
                game.setScreen(
                        new PlayingGamesScreen(game, MenuScreen.this, option));
            }
        }, 0.75f);// 750ms
    }
    //**** Metódo para navegar para tela de Ranking by Bruno e Carlos
    private void navigateToRankingScreen() {
        transitionState = states.fadeOut;
        Timer.schedule(new Task() {
            @Override
            public void run() {
                transitionState = states.doNothing;
                game.setScreen(
                        new RankScreen(game,MenuScreen.this));
            }
        }, 0.75f);// 750ms
    }

}