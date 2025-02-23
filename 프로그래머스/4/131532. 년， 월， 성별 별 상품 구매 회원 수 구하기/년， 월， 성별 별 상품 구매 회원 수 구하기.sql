SELECT YEAR(S.SALES_DATE) AS YEAR, MONTH(S.SALES_DATE) AS MONTH, U.GENDER, COUNT(DISTINCT S.USER_ID) AS USERS
FROM ONLINE_SALE S
LEFT JOIN USER_INFO U
ON S.USER_ID = U.USER_ID
GROUP BY YEAR, MONTH, GENDER
HAVING GENDER IS NOT NULL
ORDER BY YEAR, MONTH, GENDER;