package tennisKataPackage;

public class GameOverException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	GameOverException()
	{
		super();
	}

	GameOverException(String errorMessage)
	{
        super(errorMessage);
    }
	
	GameOverException(Throwable cause)
	{
		super(cause);
	}
	
	GameOverException(String errorMessage, Throwable cause)
	{
		super(errorMessage, cause);
	}

	GameOverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
