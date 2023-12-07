package com.bsuir.skripskaya.wt3.lab3.logic;

import java.io.IOException;

/**
 * This interface represents basic interface of controller commands.
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public interface ICommand {
    /**
     * This method executes the command.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @param context  The servlet context.
     * @return The name of the page to redirect to.
     * @throws ServletException If an error occurs during execution.
     * @throws IOException      If an error occurs during I/O.
     */
    PageName completeCommand() throws  IOException;
}
