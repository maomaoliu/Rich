/**
 * 
 */
package tw.homework.rich.game.position;

import java.util.LinkedList;
import java.util.List;

import tw.homework.rich.game.classes.Prop;
import tw.homework.rich.game.exception.PositionIsUsingException;
import tw.homework.rich.game.player.Role;

/**
 * @author noam yacht2005@gmail.com Created at：2012-1-13
 */
public abstract class Position {
	int number;
	List<Role> roles;
	Prop prop;

	public Position() {
		roles = new LinkedList<Role>();
	}

	public abstract String getMark();

	public void addRole(Role role) {
		roles.add(role);
	}

	public boolean removeRole(Role role) {
		return roles.remove(role);
	}

	public boolean hasRole() {
		return !roles.isEmpty();
	}

	public Role getLatestRole() {
		if (hasRole())
			return roles.get(roles.size() - 1);
		else
			return null;
	}

	public boolean hasProp() {
		return prop != null;
	}

	public void addProp(Prop prop) throws PositionIsUsingException {
		if (prop == null && !hasRole())
			this.prop = prop;
		else
			throw new PositionIsUsingException();
	}

	public void removeProp() {
		prop = null;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Prop getProp() {
		return this.prop;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if(obj instanceof Position){
			Position position = (Position) obj;
			if(this.number == position.number)
				return true;
			return false;
		}
		return false;
	}
	
}
