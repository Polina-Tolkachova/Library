package by.polina.library.command;

/**
 * Created by Polina Tolkchyova on 01.08.2015.
 */

/**
 * involves enumeration for parameters (kinds of commands)
 * creates an  object of necessary Command Class
 */
public enum CommandEnum {

    CHANGE_BAN_STATUS {
        {
            this.command = new ChangeBanStatusCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    DELETE_BOOK {
        {
            this.command = new DeleteBookCommand();
        }
    },
    DELETE_ORDER {
        {
            this.command = new DeleteOrderCommand();
        }
    },
    PATH_UPDATE_BOOK_PAGE {
        {
            this.command = new PathUpdateBookCommand();
        }
    },
    PATH_ORDER_PAGE {
        {
            this.command = new PathOrderPageCommand();
        }
    },
    ORDER_BOOK {
        {
            this.command = new OrderBookCommand();
        }
    },
    PATH_LOGIN_PAGE {
        {
            this.command = new PathLoginPageCommand();
        }
    },
    LOGIN_LOGOUT {
        {
            this.command = new LoginLogoutCommand();
        }
    },
    TAKE_BOOK_BY_ID {
        {
            this.command = new TakeBookByIdCommand();
        }
    },
    TAKE_BOOKS {
        {
            this.command = new TakeAllBooksCommand();
        }
    },
    PATH_REGISTRATION_PAGE {
        {
            this.command = new PathRegistrationCommand();
        }
    },
    READER_LIST {
        {
            this.command = new ReaderListCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    ADD_UPDATE_BOOK {
        {
            this.command = new AddUpdateBookCommand();
        }
    },
    TAKE_INFO_ABOUT_BOOK {
        {
            this.command = new TakeInfoAboutBookCommand();
        }
    },
    VERIFY_LOGIN {
        {
            this.command = new VerifyLoginInfoCommand();
        }
    },
    SEARCH_BOOK {
        {
            this.command = new SearchBookByTitleCommand();
        }
    },
    SELECT_BY_CATEGORY {
        {
            this.command = new SearchBookByCategoryCommand();
        }
    };

    ActionCommand command;

    /**
     * gets an object of concrete ~Command class
     *
     * @return command
     */
    public ActionCommand getCommand() {
        return command;
    }
}


