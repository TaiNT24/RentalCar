--Trigger when Status = 'Paymented' => renting
IF OBJECT_ID('trg_AvailableQuantity', 'TR') IS NOT NULL
	DROP trigger trg_AvailableQuantity
GO
CREATE TRIGGER trg_AvailableQuantity ON dbo.RentCar AFTER UPDATE AS
BEGIN
--
	DECLARE @QuantityRent INT, @StatusRent VARCHAR(50)
	
	SELECT @StatusRent = (SELECT Status FROM Inserted)
							
	
	IF(@StatusRent = 'Paymented')
		BEGIN
			SELECT @QuantityRent = (SELECT SUM(Quantity)
									FROM dbo.RentCar
									WHERE Status='Paymented' 
										AND IDCar = (SELECT IDCar FROM Inserted)
									)
			IF(@QuantityRent > 0)
				BEGIN
					UPDATE dbo.Car SET AvailableQuantity = Car.Quantity - @QuantityRent
							WHERE Car.IDCar = (SELECT IDCar FROM Inserted)
					
				END
		END
END
GO
------------------------------------------------------------
------------------------------------------------------------
--Trigger when Status = 'Returned' => return the rental car
IF OBJECT_ID('trg_ReturnQuantity', 'TR') IS NOT NULL
	DROP trigger trg_ReturnQuantity
GO
CREATE TRIGGER trg_ReturnQuantity ON dbo.RentCar AFTER UPDATE, INSERT AS
BEGIN

	DECLARE @QuantityReturn INT, @StatusRent VARCHAR(50)
	
	SELECT @StatusRent = (SELECT Status FROM Inserted)
							
	
	IF(@StatusRent = 'Returned' OR @StatusRent = 'Canceled')
		BEGIN
			SELECT @QuantityReturn = (SELECT Inserted.Quantity FROM Inserted)
			
			

			IF(@QuantityReturn > 0)
				BEGIN
					UPDATE dbo.Car 
					SET AvailableQuantity += @QuantityReturn
					--Car.AvailableQuantity + @QuantityReturn
					WHERE Car.IDCar = (SELECT IDCar FROM Inserted)
				END
		END
	ELSE
		--Trigger Update Total Price In RentCar
		BEGIN
			-- Update total price in a RENT_CART
			UPDATE dbo.RentCar 
			SET TotalPrice = (SELECT Quantity*Price FROM dbo.RentCar WHERE IDRent = (SELECT Inserted.IDRent FROM Inserted))
			WHERE IDRent = (SELECT Inserted.IDRent FROM Inserted)
	
			-- Update total price in a CART
			UPDATE dbo.Cart 
			SET TotalPrice = (SELECT SUM(TotalPrice) 
								FROM dbo.RentCar 
								WHERE IDCart = (SELECT Inserted.IDCart FROM Inserted))
			WHERE Cart.IDCart = (SELECT Inserted.IDCart FROM Inserted)
		END
END
GO
------------------------------------------------------------
------------------------------------------------------------

------------------------------------------------------------
------------------------------------------------------------
--Trigger Update Total Price In Car when user delete a car in RentCar
IF OBJECT_ID('trg_UpdateTotalPriceInCartWhenDeleteRentCar', 'TR') IS NOT NULL
	DROP trigger trg_UpdateTotalPriceInCartWhenDeleteRentCar
GO
CREATE TRIGGER trg_UpdateTotalPriceInCartWhenDeleteRentCar ON dbo.RentCar AFTER DELETE AS
BEGIN
	
	-- Update total price in a CART
	UPDATE dbo.Cart 
	SET TotalPrice = TotalPrice - (SELECT Deleted.TotalPrice FROM Deleted)

	WHERE Cart.IDCart = (SELECT Deleted.IDCart FROM Deleted)

END
GO