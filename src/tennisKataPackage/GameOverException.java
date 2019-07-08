package tennisKataPackage;

public class GameOverException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	GameOverException(String errorMessage)
	{
        super(errorMessage);
    }
}
