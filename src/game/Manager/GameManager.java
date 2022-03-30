package game.Manager;

public abstract class GameManager {

    public abstract void StartGameFramework();

    public abstract void StopGameFramework();

    protected abstract void GameFramework();

    protected abstract void OneFrame();

    protected abstract void GameOver();
}