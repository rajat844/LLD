# Digital Wallet

## Requirements

1. User can register to the wallet and add the accounts for payments and set one as the default.
2. User can use different payment methods like card, upi and netbanking or send money peer to peer handled by payment strategies.
3. Every transaction records an immutable LedgerEntry tracking the source account, destination account, amount, timestap and state.
4. User can query their history globally across the wallet.

## Non Functional Requirements

1. Every payment request will be thread safe and idempotent.
2. If crediting fails after amount is debited from payee account, system must execute the compensatory rollback.
3. System supports new payment methods in future following open/closed principle.

## Entities

1. User
2. Account
3. Transaction
4. Wallet Manager
5. Account Type
6. Transaction State
7. Payment Strategy
8. User Repository
9. Transaction Repository
