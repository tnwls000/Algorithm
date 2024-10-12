SELECT USER_ID, PRODUCT_ID FROM ONLINE_SALE 
GROUP BY USER_ID, PRODUCT_ID
having count(user_id) >= 2
order by user_id, product_id desc;