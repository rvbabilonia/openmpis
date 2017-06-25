/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008-2017  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nz.co.vincenzo.openmpis.common.database;

import com.sleepycat.je.Environment;
import com.sleepycat.je.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.SimpleTransactionStatus;

/**
 * The Berkeley DB Java Edition implementation of {@link PlatformTransactionManager}.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
@Component
public class BerkeleyTransactionManager implements PlatformTransactionManager {

    private final Environment environment;
    private Transaction transaction;

    /**
     * Default constructor.
     *
     * @param environment the {@link Environment}
     */
    public BerkeleyTransactionManager(Environment environment) {
        this.environment = environment;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
        transaction = environment.beginTransaction(null, null);
        return new SimpleTransactionStatus();
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        transaction.commit();
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        transaction.abort();
    }

    /**
     * Returns the parent {@link Transaction}.
     *
     * @return the parent {@link Transaction}
     */
    public Transaction getTransaction() {
        return transaction;
    }
}
