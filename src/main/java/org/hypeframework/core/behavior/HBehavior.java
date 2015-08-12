package org.hypeframework.core.behavior;

import org.hypeframework.core.collection.HNode;
import org.hypeframework.H;
import processing.core.PApplet;

public abstract class HBehavior extends HNode<HBehavior> {
	protected HBehaviorRegistry _registry;

	public HBehavior register() {
		H.behaviors().register(this);
		return this;
	}

	public HBehavior unregister() {
		H.behaviors().unregister(this);
		return this;
	}

	@Override
	public boolean poppedOut() {
		return _registry == null;
	}

	@Override
	public void popOut() {
		super.popOut();
		_registry = null;
	}

	@Override
	public void swapLeft() {
		if(_prev._prev == null) return;
		super.swapLeft();
	}

	@Override
	public void putAfter(HBehavior dest) {
		if(dest._registry == null) return;

		super.putAfter(dest);
		_registry = dest._registry;
	}

	@Override
	public void putBefore(HBehavior dest) {
		if(dest._registry == null) return;

		super.putBefore(dest);
		_registry = dest._registry;
	}

	@Override
	public void replaceNode(HBehavior target) {
		super.replaceNode(target);
		_registry = target._registry;
		target._registry = null;
	}

	public abstract void runBehavior(PApplet app);
}