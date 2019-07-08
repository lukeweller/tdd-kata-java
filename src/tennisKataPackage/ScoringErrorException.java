package tennisKataPackage;

public class ScoringErrorException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	ScoringErrorException(String errorMessage)
	{
        super(errorMessage);
    }
}
