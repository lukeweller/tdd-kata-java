package tddKataPackage;

public class NegativeNumberException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	NegativeNumberException(String errorMessage)
	{
        super(errorMessage);
    }
}
