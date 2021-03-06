/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 or later
 * as published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005 - 2014
 *     The copyright to this program is held by it's authors.
 *
 */
import java.util.List;

import org.crosswire.jsword.book.Book;
import org.crosswire.jsword.book.BookFilters;
import org.crosswire.jsword.book.Books;
import org.crosswire.jsword.book.test.Speed;

/**
 * Bench is a command line utility that runs the Speed benchmark program.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class Bench {
    /**
     * Basic constructor
     */
    public static void main(String[] args) {
        Book version = null;

        if (args.length == 0) {
            usage();
            versions();
            //System.exit(1);
        }

        List<Book> dicts = Books.installed().getBooks(BookFilters.getOnlyBibles());
        version = dicts.get(0);

        Speed speed = new Speed(version);
        speed.run();

        double time = speed.getBenchmark() / 1000.0;
        System.out.println("CBench mark for '" + version + "': " + time + "s");
    }

    /**
     * Print a usage message to stdout
     */
    private static void usage() {
        System.out.println("Usage: CBench [<version>] [disk]");
        System.out.println("  where <version> is the name of a version to benchmark.");
        System.out.println("  and 'disk' specifies if the Raw version should not cache data.");
        System.out.println("  Remember to quote the version name if it includes spaces.");
    }

    /**
     * List the available versions
     */
    private static void versions() {
        System.out.println("  Available versions:");
        List<Book> lbmds = Books.installed().getBooks();
        for (Book book : lbmds) {
            System.out.println("    " + book.getName());
        }
    }
}
