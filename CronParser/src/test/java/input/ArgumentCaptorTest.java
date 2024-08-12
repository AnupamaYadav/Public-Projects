package input;

import com.deliveroo.assignment.constant.ErrorConstants;
import com.deliveroo.assignment.exception.ApplicationException;
import com.deliveroo.assignment.input.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentCaptorTest {

    @Test
    public void testValidArgumentInput() {
        String[] args = { "*/15 0 1,15 * 1-5 /usr/bin/fin"};
        String result = ArgumentCaptor.captureInput(args);
        assertEquals("*/15 0 1,15 * 1-5 /usr/bin/fin", result);
    }

    @Test
    public void testEmptyArgumentInput() {
        String[] args = new String[]{};
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            ArgumentCaptor.captureInput(args);
        });

        assertEquals(ErrorConstants.ERROR_INVALID_INPUT, exception.getErrorCode());
        assertEquals(ErrorConstants.ERROR_INVALID_INPUT_MSG, exception.getUserMessage());
    }

    @Test
    public void testNullArgumentInput() {
        ApplicationException exception = assertThrows(ApplicationException.class, () -> {
            ArgumentCaptor.captureInput(null);
        });

        assertEquals(ErrorConstants.ERROR_INVALID_INPUT, exception.getErrorCode());
        assertEquals(ErrorConstants.ERROR_INVALID_INPUT_MSG, exception.getUserMessage());
    }


}
