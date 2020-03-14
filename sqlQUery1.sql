SELECT IDCar FROM dbo.RentCar
WHERE IDRent IN ( SELECT IDRent 
					FROM dbo.DateTimeRentCar
					WHERE DateRent > '02/26/2019' OR DateReturn < '02/21/2019'
					)
		AND Status='Paymented'
					-- mac du available trong CAr = 0, 
					-- chọn ra ID Car ngoài những ngày trong bảng có ng thuê, status Paymented: means đang có ng thuê
					
UNION

SELECT IDCar FROM dbo.Car WHERE AvailableQuantity>0

GO



---------------------------------------------------
SELECT IDCar, CarName, Color, YearProduce,
              Category, Price, Quantity 
               FROM Car 
                WHERE IDCar IN (SELECT IDCar FROM dbo.RentCar
								WHERE IDRent IN ( SELECT IDRent 
													FROM dbo.DateTimeRentCar
													WHERE DateRent > '02/28/2019' OR DateReturn < '02/24/2019')

												UNION

												SELECT IDCar FROM dbo.Car WHERE AvailableQuantity>0)
